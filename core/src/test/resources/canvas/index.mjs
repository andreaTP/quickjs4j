import { Jimp } from "jimp";

export async function print(original_image) {
    try {
        const gifBytes = // new TextEncoder().encode(original_image);
        new Uint8Array([
            0x47, 0x49, 0x46, 0x38, 0x39, 0x61,  // GIF89a
            0x01, 0x00, 0x01, 0x00, 0x80, 0x00, 0x00,  // width=1, height=1, flags
            0xff, 0xff, 0xff, 0x00, 0x00, 0x00,  // color table
            0x21, 0xf9, 0x04, 0x01, 0x00, 0x00, 0x00, 0x00,  // graphics control extension
            0x2c, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x01, 0x00, 0x00,  // image descriptor
            0x02, 0x02, 0x44, 0x01, 0x00,  // image data
            0x3b  // trailer
        ]);

        const image = await Jimp.fromBuffer(gifBytes.buffer);

        image.greyscale();

        return "pippo " + (await image.getBase64("image/jpeg"));
    } catch (err) {
        console.log("ERROR! " + err);
        console.log("Stack:", err.stack);
        return "error: " + err.message;
    }
}
