package io.github.mcchampions.DodoOpenJava.test;

import io.github.mcchampions.DodoOpenJava.Card.Accessory;
import io.github.mcchampions.DodoOpenJava.Card.Card;
import io.github.mcchampions.DodoOpenJava.Card.Section;
import io.github.mcchampions.DodoOpenJava.Card.enums.*;

import java.util.HashMap;

public class cardTest {
    public static void main(String[] args) {
        Card card = new Card();
        HashMap<RemarkType,String> map = new HashMap<>();
        map.put(RemarkType.Image,"https://img.imdodo.com/openapitest/upload/cdn/88E7BB23780FE9C887D49B11ECC79C3F_1674142828338.jpg");
        map.put(RemarkType.Markdown,"À¶¾¨DAO");
        card.addTextRemarkComponent(map);

        Section section = new Section();
        section.editContent("test");
        section.editContentType(TextType.Markdown);


        Accessory accessory = new Accessory();
        accessory.addAccessory(Color.Default,"test", ButtonAction.copy_content,"ssss");

        card.addSection(Align.Left,section,accessory);
        System.out.println(card.toString());
    }
}
