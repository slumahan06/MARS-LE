revolt $t0, $t1 # Set $t0 to 13 (states) and $t1 to 50 (stability)

pelect $t3, $t4 # Elect a President, Team One EV's in $t3 and Team Two in $t4
melect $t5, $t6 # $t5 is the House, $t6 is the Senate

gov $t3, $t5, $t6 # Print government information
switch $t5, $t6 # Switch party control of chambers in Congress

pelect $t3, $t4 # Hold another election; old results are stored in stored temporary registers

query $t3, $t4 # Flip a coin to see if new election results are reverted

ref $0, 13 # Hold referendums in all 13 states to see who wants to leave the Union