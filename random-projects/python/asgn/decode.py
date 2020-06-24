import binascii, sys

print(binascii.a2b_base64(sys.argv[1]).decode('ascii'))