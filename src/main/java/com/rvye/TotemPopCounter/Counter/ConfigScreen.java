package com.rvye.TotemPopCounter.Counter;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

@SuppressWarnings("unused")
public class ConfigScreen extends BadConfigScreen {
    public ConfigScreen(Screen parent) {
        super(parent, Text.literal("TotemCounter Config"));
    }

    @Override
    protected SimpleOption<?>[] getOptions() {
        return new SimpleOption[0];
    }
}
