package ru.geekbrains.android3_6.di.modules;

import android.widget.ImageView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3_6.model.cache.ImageCache;
import ru.geekbrains.android3_6.model.image.ImageLoader;
import ru.geekbrains.android3_6.model.image.android.ImageLoaderGlide;
import ru.geekbrains.android3_6.model.image.android.ImageLoaderPicasso;

@Module(includes = ImageCacheModule.class)
public class ImageLoaderModule {

    @Provides
    @Named("glide")
    public ImageLoader<ImageView> imageLoaderGlide(ImageCache imageCache) {
        return new ImageLoaderGlide(imageCache);
    }

    @Provides
    @Named("picasso")
    public ImageLoader<ImageView> imageLoaderPicasso() {
        return new ImageLoaderPicasso();
    }
}
