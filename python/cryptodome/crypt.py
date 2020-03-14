from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_OAEP
import binascii

pubkey = RSA.import_key(open("public.key", "rb").read())
prvkey = RSA.import_key(open("private.key", "rb").read())

euser = PKCS1_OAEP.new(pubkey).encrypt(input("Username: ").encode())
epass = PKCS1_OAEP.new(pubkey).encrypt(input("Password: ").encode())

f = open("login.info", "wb")
f.write(binascii.hexlify(euser))
f.write(b";")
f.write(binascii.hexlify(epass))
f.close()

