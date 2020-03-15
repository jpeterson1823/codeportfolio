# Returns in cents
def get_change(changestr):
    # Checks for decimal number
    if "." not in changestr:
        return int(changestr.strip())*100
    else:
        # Split and add
        change = 0
        splt = changestr.split(".")
        change += int(splt[0])*100

        if int(splt[1][0]) == 0:
            change += int(splt[1][1])
        else:
            if len(splt[1]) == 2:
                change += int(splt[1])
            else:
                change += int(splt[1])*10

        return change


# Define values
quarter, dime, nickel, penny = 25, 10, 5, 1
change = ""
while change == "":
    # Make sure that value is assigned.
    try:
        userin = input("Change owed: ")
        if "-" not in userin:  # Check for negative number
            change = get_change(userin)
    except ValueError:
        print("Change must be a valid number!")

coins = 0

# Get coins
while change > 0:
    if change >= quarter:
        change -= quarter
        coins += 1
    elif change >= dime:
        change -= dime
        coins += 1
    elif change >= nickel:
        change -= nickel
        coins += 1
    else:
        change -= penny
        coins += 1

print(coins)