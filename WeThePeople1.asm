revolt $t0, $t1 # Set $t0 to 13 (states) and $t1 to 50 (stability)

factory $t2 # Build factories to increase GDP in $t2
factory $t2
factory $t2

Loop:
corr $t1 # Decrease stability by 15
nun $t0, $t1, END # Check if either the number of states or stability is less than or equal to 0
j Loop

END: