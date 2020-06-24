from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_OAEP
import binascii

keyPair = RSA.generate(3072)

pubKey = keyPair.publickey()
#print(pubKey.exportKey())
#print(f"Public key:  (n={hex(pubKey.n)}, e={hex(pubKey.e)})")
#pubKeyPEM = pubKey.exportKey()
#print(pubKeyPEM.decode('ascii'))

#print(keyPair.exportKey())
#print(f"Private key: (n={hex(pubKey.n)}, d={hex(keyPair.d)})")
#privKeyPEM = keyPair.exportKey()
#print(privKeyPEM.decode('ascii'))

privatekey = open("private.key", "wb")
privatekey.write(keyPair.exportKey())
privatekey.close()

publickey = open("public.key", "wb")
publickey.write(keyPair.publickey().exportKey())
publickey.close()