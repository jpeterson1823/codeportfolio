
hira = "あえいうかさたなはまやらわがざだばぱきしちにひみりぎじぢびぴくすつぬふむゆるぐずづぶぷけせてねへめれげぜでべぺおこそとのほもよろをごぞどぼぽん"
kata = "アカサタナハマヤラワガザダバパイキシチニヒミリギジヂビピウクスツヌフムユルグズヅブプエケセテネヘメレゲゼデベペオコソトノホモヨロヲゴゾドボポン"

shira = "ぁゃぃぅゅぇぉょっ"
skata = "ァャィゥュェォョッ"

punct = "ー、。？"

data = open("test.txt").read()
new_data = ""

skip = False

for i in range(len(data)):
    if not skip:
        if data[i] != 'a' and data[i] != 'e' and data[i] != 'i' and data[i] != 'o' and data[i] != 'u':
            if data[i] == 'k':
                if data[i+1] == 'a': new_data += 'か'
                elif data[i+1] == 'e': new_data += 'け'
                elif data[i+1] == 'i': new_data += 'き'
                elif data[i+1] == 'o': new_data += 'こ'
                elif data[i+1] == 'u': new_data += 'く'
                else : data += "+=+"
            if data[i] == 's':
                if data[i+1] == 'a': new_data += 'さ'
                elif data[i+1] == 'e': new_data += 'せ'
                elif data[i+1] == 'o': new_data += 'そ'
                elif data[i+1] == 'u': new_data += 'す'
                else : data += "+=+"
            if data[i] == 'h':
                if data[i+1] == 'a': new_data += 'は'
                elif data[i+1] == 'e': new_data += 'へ'
                elif data[i+1] == 'i': new_data += 'ひ'
                elif data[i+1] == 'o': new_data += 'ほ'
                else : data += "+=+"
            if data[i] == 'n':
                if data[i+1] == 'a': new_data += 'な'
                elif data[i+1] == 'e': new_data += 'ね'
                elif data[i+1] == 'i': new_data += 'に'
                elif data[i+1] == 'o': new_data += 'の'
                elif data[i+1] == 'u': new_data += 'ぬ'
                else : data += 'ん'
            
            skip = True
        else:
            if data[i] == 'a' : new_data += 'あ'
            elif data[i] == 'e' : new_data += 'え'
            elif data[i] == 'i' : new_data += 'い'
            elif data[i] == 'o' : new_data += 'お'
            elif data[i] == 'u' : new_data += 'う'
            else : new_data += "+_+"
    else : skip = False
    print(data[i])

print(new_data)