package com.rvye.TotemPopCounter.Counter;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;


@SuppressWarnings("unused")
public class util {

    public static final Text UNFINISHED = Text.of("not finished");
    public static SimpleOption<Boolean> createButton(String key, Object value, Consumer<Screen> callback) {
        return createButton(key, Text.of(String.valueOf(value)), callback);
    }

    public static SimpleOption<Boolean> createButton(String key, Consumer<Screen> callback) {
        return createButton(key, UNFINISHED, callback);
    }

    public static SimpleOption<Boolean> createButton(String key, Text text, Consumer<Screen> callback) {
        return new SimpleOption<>(key, SimpleOption.emptyTooltip(), (optionText, value) -> text,
                SimpleOption.BOOLEAN, true, v -> callback.accept(MinecraftClient.getInstance().currentScreen));
    }

    public static SimpleOption<Boolean> createOpenButton(String key, Object value, UnaryOperator<Screen> callback) {
        return createOpenButton(key, Text.of(String.valueOf(value)), callback);
    }

    public static SimpleOption<Boolean> createOpenButton(String key, UnaryOperator<Screen> callback) {
        return createOpenButton(key, UNFINISHED, callback);
    }

    public static SimpleOption<Boolean> createOpenButton(String key, Text text, UnaryOperator<Screen> callback) {
        return new SimpleOption<>(key, SimpleOption.emptyTooltip(), (optionText, value) -> text,
                SimpleOption.BOOLEAN, true, v -> MinecraftClient.getInstance().setScreen(callback.apply(MinecraftClient.getInstance().currentScreen)));
    }

    public static ButtonWidget doneButton(int width, int height, Screen parent) {
        StringBuilder builder = new StringBuilder();
        return ButtonWidget.builder(ScreenTexts.DONE, button -> MinecraftClient.getInstance().setScreen(parent))

                .dimensions(width / 2 - 100, height - 27, 200, 20)
                .build();
    }

    public static Tuple2<Integer, Integer> getTextCoords(Text text, int screenWidth, TextRenderer textRenderer, int x, int y, int width, int height) {
        int rx = x - ((screenWidth - width) / 2);
        int textX = x + (width / 2) - (textRenderer.getWidth(text) / 2);
        int textY = y + height + 2 - textRenderer.fontHeight;

        if (Math.abs(rx) >= 2) {
            textY = y + (height / 2) - (textRenderer.fontHeight / 2);

            if (rx < 0) textX = x + width + 2;
            else textX = x - 2 - textRenderer.getWidth(text);
        }

        return new Tuple2<>(textX, textY);
    }

    public static Tuple2<Integer, Integer> getTextCoords(Text text, int screenWidth, TextRenderer textRenderer, int x, int y) {
        return getTextCoords(text, screenWidth, textRenderer, x, y, 16, 16);
    }

    public static double bound(double n, double min, double max) {
        if (min > max) {
            double tmp = max;
            max = min;
            min = tmp;
        }

        return Math.min(Math.max(n, min), max);
    }

    public static int bound(int n, int min, int max) {
        if (min > max) {
            int tmp = max;
            max = min;
            min = tmp;
        }

        return Math.min(Math.max(n, min), max);
    }

    public static String getText(OrderedText text) {
        StringBuilder builder = new StringBuilder();
        text.accept((index, style, codePoint) -> {
            builder.append(Character.toChars(codePoint));
            return true;
        });

        return builder.toString();
    }

    public record Tuple2<T1, T2>(T1 t1, T2 t2) {
    }

    private util() {}
}