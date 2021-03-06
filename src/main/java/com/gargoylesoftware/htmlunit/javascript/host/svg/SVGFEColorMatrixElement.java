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
package com.gargoylesoftware.htmlunit.javascript.host.svg;

import static com.gargoylesoftware.htmlunit.javascript.configuration.SupportedBrowser.CHROME;
import static com.gargoylesoftware.htmlunit.javascript.configuration.SupportedBrowser.FF60;
import static com.gargoylesoftware.htmlunit.javascript.configuration.SupportedBrowser.FF68;

import com.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.gargoylesoftware.htmlunit.javascript.configuration.JsxConstant;
import com.gargoylesoftware.htmlunit.javascript.configuration.JsxConstructor;
import com.gargoylesoftware.htmlunit.svg.SvgFeColorMatrix;

/**
 * A JavaScript object for {@code SVGFEColorMatrixElement}.
 *
 * @author Ahmed Ashour
 */
@JsxClass(domClass = SvgFeColorMatrix.class)
public class SVGFEColorMatrixElement extends SVGElement {

    /** The constant {@code SVG_FECOLORMATRIX_TYPE_UNKNOWN}. */
    @JsxConstant
    public static final int SVG_FECOLORMATRIX_TYPE_UNKNOWN = 0;
    /** The constant {@code SVG_FECOLORMATRIX_TYPE_MATRIX}. */
    @JsxConstant
    public static final int SVG_FECOLORMATRIX_TYPE_MATRIX = 1;
    /** The constant {@code SVG_FECOLORMATRIX_TYPE_SATURATE}. */
    @JsxConstant
    public static final int SVG_FECOLORMATRIX_TYPE_SATURATE = 2;
    /** The constant {@code SVG_FECOLORMATRIX_TYPE_HUEROTATE}. */
    @JsxConstant
    public static final int SVG_FECOLORMATRIX_TYPE_HUEROTATE = 3;
    /** The constant {@code SVG_FECOLORMATRIX_TYPE_LUMINANCETOALPHA}. */
    @JsxConstant
    public static final int SVG_FECOLORMATRIX_TYPE_LUMINANCETOALPHA = 4;

    /**
     * Creates an instance.
     */
    @JsxConstructor({CHROME, FF68, FF60})
    public SVGFEColorMatrixElement() {
    }
}
