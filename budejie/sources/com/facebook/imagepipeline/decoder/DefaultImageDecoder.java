package com.facebook.imagepipeline.decoder;

import com.facebook.common.internal.Closeables;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import java.io.InputStream;
import java.util.Map;
import javax.annotation.Nullable;

public class DefaultImageDecoder implements ImageDecoder {
    private final ImageDecoder mAnimatedGifDecoder;
    private final ImageDecoder mAnimatedWebPDecoder;
    @Nullable
    private final Map<ImageFormat, ImageDecoder> mCustomDecoders;
    private final ImageDecoder mDefaultDecoder;
    private final PlatformDecoder mPlatformDecoder;

    public DefaultImageDecoder(ImageDecoder imageDecoder, ImageDecoder imageDecoder2, PlatformDecoder platformDecoder) {
        this(imageDecoder, imageDecoder2, platformDecoder, null);
    }

    public DefaultImageDecoder(ImageDecoder imageDecoder, ImageDecoder imageDecoder2, PlatformDecoder platformDecoder, @Nullable Map<ImageFormat, ImageDecoder> map) {
        this.mDefaultDecoder = new DefaultImageDecoder$1(this);
        this.mAnimatedGifDecoder = imageDecoder;
        this.mAnimatedWebPDecoder = imageDecoder2;
        this.mPlatformDecoder = platformDecoder;
        this.mCustomDecoders = map;
    }

    public CloseableImage decode(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        if (imageDecodeOptions.customImageDecoder != null) {
            return imageDecodeOptions.customImageDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
        }
        Object imageFormat = encodedImage.getImageFormat();
        if (imageFormat == null || imageFormat == ImageFormat.UNKNOWN) {
            imageFormat = ImageFormatChecker.getImageFormat_WrapIOException(encodedImage.getInputStream());
            encodedImage.setImageFormat(imageFormat);
        }
        if (this.mCustomDecoders != null) {
            ImageDecoder imageDecoder = (ImageDecoder) this.mCustomDecoders.get(imageFormat);
            if (imageDecoder != null) {
                return imageDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
            }
        }
        return this.mDefaultDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
    }

    public CloseableImage decodeGif(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        InputStream inputStream = encodedImage.getInputStream();
        if (inputStream == null) {
            return null;
        }
        try {
            CloseableImage decodeStaticImage;
            if (imageDecodeOptions.forceStaticImage || this.mAnimatedGifDecoder == null) {
                decodeStaticImage = decodeStaticImage(encodedImage, imageDecodeOptions);
                Closeables.closeQuietly(inputStream);
                return decodeStaticImage;
            }
            decodeStaticImage = this.mAnimatedGifDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
            return decodeStaticImage;
        } finally {
            Closeables.closeQuietly(inputStream);
        }
    }

    public CloseableStaticBitmap decodeStaticImage(EncodedImage encodedImage, ImageDecodeOptions imageDecodeOptions) {
        CloseableReference decodeFromEncodedImage = this.mPlatformDecoder.decodeFromEncodedImage(encodedImage, imageDecodeOptions.bitmapConfig);
        try {
            CloseableStaticBitmap closeableStaticBitmap = new CloseableStaticBitmap(decodeFromEncodedImage, ImmutableQualityInfo.FULL_QUALITY, encodedImage.getRotationAngle());
            return closeableStaticBitmap;
        } finally {
            decodeFromEncodedImage.close();
        }
    }

    public CloseableStaticBitmap decodeJpeg(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        CloseableReference decodeJPEGFromEncodedImage = this.mPlatformDecoder.decodeJPEGFromEncodedImage(encodedImage, imageDecodeOptions.bitmapConfig, i);
        try {
            CloseableStaticBitmap closeableStaticBitmap = new CloseableStaticBitmap(decodeJPEGFromEncodedImage, qualityInfo, encodedImage.getRotationAngle());
            return closeableStaticBitmap;
        } finally {
            decodeJPEGFromEncodedImage.close();
        }
    }

    public CloseableImage decodeAnimatedWebp(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        return this.mAnimatedWebPDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
    }
}
