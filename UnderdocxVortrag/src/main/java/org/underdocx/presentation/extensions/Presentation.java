/*
MIT License

Copyright (c) 2024 Gerald Winter

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package org.underdocx.presentation.extensions;

import org.odftoolkit.odfdom.doc.OdfPresentationDocument;
import org.underdocx.common.cli.EngineProvider;
import org.underdocx.common.types.Resource;
import org.underdocx.doctypes.EngineAPI;
import org.underdocx.doctypes.odf.odp.OdpContainer;
import org.underdocx.doctypes.odf.odp.OdpEngine;
import org.underdocx.environment.UnderdocxEnv;

public class Presentation implements EngineProvider<OdpContainer, OdfPresentationDocument> {
    @Override
    public OdpContainer load(Resource r) throws Exception {
        return new OdpContainer(r);
    }

    @Override
    public EngineAPI<OdpContainer, OdfPresentationDocument> createEngine() {
        UnderdocxEnv.getInstance().isDebug = true;
        OdpEngine engine = new OdpEngine();
        engine.registerParametersCommandHandler(new RemovePreviousPagesCommandHandler(engine.getModifiers()));
        return engine;
    }
}
