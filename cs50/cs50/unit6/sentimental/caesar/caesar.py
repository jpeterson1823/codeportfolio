import sys

# Check args
if len(sys.argv) != 2:
    print("Usage: ./caesar [key]")
    sys.exit(1)
else:
    # Set k and p values
    k = int(sys.argv[1])
    p = input("plaintext: ")

    # ci=(pi+k)mod26
    ctext = ""
    for ch in p:
        if ch.isupper():
            ctext += chr(((ord(ch) + k) - ord('A')) % 26 + ord('A'))
        elif ch.islower():
            ctext += chr(((ord(ch) + k) - ord('a')) % 26 + ord('a'))
        else:
            ctext += ch

    print("ciphertext: "+ctext)