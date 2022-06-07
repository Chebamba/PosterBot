package com.chatbot.PosterBot.util;

import com.vdurmont.emoji.EmojiParser;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Emojis {

    SUCCESS_MARK(EmojiParser.parseToUnicode(":check_mark:")),
    FAIL_MARK(EmojiParser.parseToUnicode(":cross_mark:")),
    HELP(EmojiParser.parseToUnicode(":mage:"));

    private final String emojiName;

    @Override
    public String toString() {
        return emojiName;
    }
}
