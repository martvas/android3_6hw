package ru.geekbrains.android3_6.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3_6.model.cache.ImageCache;

@Module
public class ImageCacheModule {
    @Provides
    public ImageCache imageCache() {
        return new ImageCache();
    }
}
