def lengthOfLongestSubstring(s: str) -> int:
    seen = ""
    lsub = ""
    sub = ""

    for c in s:
        print(seen,lsub,sub)
        if c not in seen:
            seen += c
            sub += c
        else:
            seen = ""
            lsub = sub if len(sub) > len(lsub) else lsub
            sub = ""
        
    lsub = sub if len(sub) > len(lsub) else lsub
    print(lsub)
    return len(lsub)

def main():
    print(lengthOfLongestSubstring('aab'))

if __name__ == '__main__':
    main()
