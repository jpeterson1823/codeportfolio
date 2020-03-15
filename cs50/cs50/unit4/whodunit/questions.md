# Questions

## What's `stdint.h`?

Library for integers allowing for more options when using them.

## What's the point of using `uint8_t`, `uint32_t`, `int32_t`, and `uint16_t` in a program?

To specify the encoding method used in the file that is being imported.

## How many bytes is a `BYTE`, a `DWORD`, a `LONG`, and a `WORD`, respectively?

1, 4, 8, 2

## What (in ASCII, decimal, or hexadecimal) must the first two bytes of any BMP file be? Leading bytes used to identify file formats (with high probability) are generally called "magic numbers."

header

## What's the difference between `bfSize` and `biSize`?

bfSize is returned as a float; biSize is returned as an integer.

## What does it mean if `biHeight` is negative?

TODO

## What field in `BITMAPINFOHEADER` specifies the BMP's color depth (i.e., bits per pixel)?

RGBQUAD

## Why might `fopen` return `NULL` in lines 24 and 32 of `copy.c`?

either the file does not exist or the file could not be created for some reason such as user privileges.

## Why is the third argument to `fread` always `1` in our code?

only one byte of the image file needs to be read at a time.

## What value does line 65 of `copy.c` assign to `padding` if `bi.biWidth` is `3`?

Line 65 is a comment, so it does nothing. Line 63 sets it to 1.

## What does `fseek` do?

sets the file pointer's position to the given offset.

## What is `SEEK_CUR`?

A parameter that moves the file pointer to a location

## Whodunit?

It was Professor Plum with a candlestick in the library.
