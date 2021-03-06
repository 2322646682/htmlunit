/*
 * Copyright (c) 2002-2020 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit.javascript.background;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;

import net.sourceforge.htmlunit.corejs.javascript.ContextFactory;
import net.sourceforge.htmlunit.corejs.javascript.Function;
import net.sourceforge.htmlunit.corejs.javascript.Scriptable;

/**
 * A helper class for the IE behavior #default#download
 * This represents a download action. The download is handled
 * asynchronously, when the download is finished, the method specified
 * by callback is called with one argument - the content of the response as string.
 * @see #startDownload(String, Function)
 * @author <a href="mailto:stefan@anzinger.net">Stefan Anzinger</a>
 */
final class DownloadBehaviorJob extends BasicJavaScriptJob {
    private static final Log LOG = LogFactory.getLog(DownloadBehaviorJob.class);

    private final URL url_;
    private final Function callback_;
    private final WebClient client_;

    /**
     * Creates a new instance.
     * @param url the URL to download
     * @param callback the callback function to call
     * @param client the client
     */
    DownloadBehaviorJob(final URL url, final Function callback, final WebClient client) {
        url_ = url;
        callback_ = callback;
        client_ = client;
    }

    /**
     * Performs the download and calls the callback method.
     */
    @Override
    public void run() {
        final Scriptable scope = callback_.getParentScope();
        final WebRequest request = new WebRequest(url_);
        try {
            final WebResponse webResponse = client_.loadWebResponse(request);
            final String content = webResponse.getContentAsString();
            if (LOG.isDebugEnabled()) {
                LOG.debug("Downloaded content: " + StringUtils.abbreviate(content, 512));
            }
            final Object[] args = new Object[] {content};
            final ContextFactory cf = ((JavaScriptEngine) client_.getJavaScriptEngine()).getContextFactory();
            cf.call(cx -> {
                callback_.call(cx, scope, scope, args);
                return null;
            });
        }
        catch (final IOException e) {
            if (LOG.isErrorEnabled()) {
                LOG.error("Behavior #default#download: Cannot download " + url_, e);
            }
        }
    }
}
