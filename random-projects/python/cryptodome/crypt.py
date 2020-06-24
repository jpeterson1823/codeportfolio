from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_OAEP
import binascii, sys

if len(sys.argv) != 2:
    print("Usage: ./crypt [-e, -d]")
    exit(1)

if sys.argv[1] == '-e':
    pubkey = RSA.import_key(open("keys/public.key", "rb").read())

    euser = PKCS1_OAEP.new(pubkey).encrypt(input("Username: ").encode())
    epass = PKCS1_OAEP.new(pubkey).encrypt(input("Password: ").encode())

    f = open("login.info", "wb")
    f.write(binascii.hexlify(euser) + b";" + binascii.hexlify(epass))
    f.close()
    print("Encrypted data and logged it to login.info.")
    exit(0)

elif sys.argv[1] == '-d':
    pvtkey = RSA.import_key(open("keys/private.key", "rb").read())

    login = [binascii.unhexlify(string) for string in open("login.info","r").read().split(";")]
    user = PKCS1_OAEP.new(pvtkey).decrypt(login[0])
    pswd = PKCS1_OAEP.new(pvtkey).decrypt(login[1])
    print(user,pswd)