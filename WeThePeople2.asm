revolt $t0, $t1 # Set $t0 to 13 (states) and $t1 to 50 (stability)

lpur $t0 # Double land
factory $t2
moon $t0, $t1
leg $t1

corr $t1 # Corruption events
corr $t1
cwar $t0
mlaw $t2, $t1 # Declare martial law!

fight $t0, $t1 # Fight rebels if any appeared after declaring martial law
actpres $t1, $t2, 10 # Let the VP take control for 10 days; stability and GDP fluctuate