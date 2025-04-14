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

package org.underdocx.elementa;

import org.underdocx.common.types.Resource;
import org.underdocx.doctypes.odf.odg.OdgContainer;
import org.underdocx.doctypes.odf.odg.OdgEngine;
import org.underdocx.enginelayers.modelengine.data.DataNode;
import org.underdocx.enginelayers.modelengine.data.simple.LeafDataNode;
import org.underdocx.enginelayers.modelengine.data.simple.MapDataNode;

import java.io.IOException;

public class BuildCards {

    public static void main(String[] args) throws IOException {
        OdgContainer doc = new OdgContainer(BuildCards.class.getResourceAsStream("./templates/demo.odg"));
        MapDataNode data = new MapDataNode(BuildCards.class.getResourceAsStream("./data/elementa.json"));
        DataNode<?> cards = data.getProperty("karten");
        OdgEngine engine = new OdgEngine();
        engine.registerStringReplacement("game", "${Import $resource:\"game\", page:\"Game\"}");
        engine.pushVariable("cards", cards);
        engine.pushVariable("images", prepareImages(cards));
        engine.pushVariable("singleCard", new Resource.ClassResource(BuildCards.class, "./templates/singlecard.odg"));
        engine.pushVariable("cardForeground", new Resource.ClassResource(BuildCards.class, "./templates/foreground.odg"));
        engine.pushVariable("cardBackground", new Resource.ClassResource(BuildCards.class, "./templates/background.odg"));
        engine.pushVariable("cardsBuilder", new Resource.ClassResource(BuildCards.class, "./templates/cardsbuilder.odg"));
        engine.pushVariable("game", new Resource.ClassResource(BuildCards.class, "./templates/game.odg"));
        engine.pushVariable("titleCard", new Resource.ClassResource(BuildCards.class, "./templates/title.odg"));
        engine.pushVariable("background", new Resource.ClassResource(BuildCards.class, "./images/titelhintergrund.png"));
        engine.pushVariable("title", data.getProperty("titel"));
        engine.pushVariable("title2", data.getProperty("titel2"));
        engine.run(doc);
        //doc.show();
        doc.showPDF();
    }


    private static DataNode<?> prepareImages(DataNode<?> list) {
        MapDataNode result = new MapDataNode();
        for (int i = 0; i < list.getSize(); i++) {
            String name = (String) list.getProperty(i).getProperty("name").getValue();
            if (!result.hasProperty(name)) {
                Resource image = new Resource.ClassResource(BuildCards.class, "./images/" + name + ".png");
                result.add(name, new LeafDataNode<>(image));
            }
            String element = (String) list.getProperty(i).getProperty("element").getValue();
            if (!result.hasProperty(element)) {
                Resource image = new Resource.ClassResource(BuildCards.class, "./images/" + element + ".png");
                result.add(element, new LeafDataNode<>(image));
            }
        }
        return result;
    }
}
