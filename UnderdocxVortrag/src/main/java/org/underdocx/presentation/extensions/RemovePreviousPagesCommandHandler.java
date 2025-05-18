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
import org.underdocx.common.tree.Nodes;
import org.underdocx.doctypes.commands.internal.AbstractTextualCommandHandler;
import org.underdocx.doctypes.modifiers.ModifiersProvider;
import org.underdocx.doctypes.modifiers.deleteplaceholder.DeletePlaceholderModifierData;
import org.underdocx.doctypes.odf.constants.OdfElement;
import org.underdocx.doctypes.odf.odp.OdpContainer;
import org.underdocx.doctypes.odf.tools.OdfNodes;
import org.underdocx.enginelayers.baseengine.CommandHandlerResult;
import org.underdocx.enginelayers.baseengine.ModifierNodeResult;
import org.w3c.dom.Node;

public class RemovePreviousPagesCommandHandler extends AbstractTextualCommandHandler<OdpContainer, OdfPresentationDocument> {
    protected RemovePreviousPagesCommandHandler(ModifiersProvider<OdpContainer, OdfPresentationDocument> modifiersProvider) {
        super("removePreviousPages", modifiersProvider);
    }

    @Override
    protected CommandHandlerResult tryExecuteTextualCommand() {
        Node placeholderNode = selection.getNode();
        Node currentPage = Nodes.findOldestAncestorNode(placeholderNode, OdfElement.PAGE).orElse(null);
        OdfNodes.getMainContentNode(selection.getDocContainer()).ifPresent(pageContainer -> {
            for (Node page : Nodes.getChildren(pageContainer, OdfElement.PAGE).collect()) {
                if (page == currentPage) {
                    break;
                } else {
                    Nodes.deleteNode(page);
                }
            }
        });
        ModifierNodeResult modifierResult = modifiers.getDeletePlaceholderModifier().modify(placeholderNode, DeletePlaceholderModifierData.DEFAULT);
        return CommandHandlerResult.FACTORY.convert(modifierResult);
    }
}
