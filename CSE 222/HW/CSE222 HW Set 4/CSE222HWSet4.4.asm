	.data

dValue:	.asciiz "This is the decimal value: "
hValue:	.asciiz "This is the hexadecimal value: "
bValue:	.asciiz "This is the binary value: "

divBySeven: .asciiz "/7 = "

subtract:.asciiz " - "
equal:	.asciiz " = "
period:	.asciiz "."
nxl:	.asciiz " \n"

	.text
main:	
	# get the random value
	la $a1, 900
	li $v0, 42
	syscall
	
	add $a0, $a0, 100 # add 100 to value to make range [100,1000)
	move $t0, $a0 # move value to $t0
	syscall
	
	# print out the decimal value value
	la $a0, dValue
	li $v0, 4
	syscall
	
	move $a0, $t0 # move value from $t0 to $a0
	li $v0, 1
	syscall
	
	# next Line
	la $a0, nxl
	li $v0, 4
	syscall
	
	# print out the hexadecimal value
	la $a0, hValue
	li $v0, 4
	syscall
	
	move $a0, $t0
	li $v0, 34
	syscall
	
	# next line
	la $a0, nxl
	li $v0, 4
	syscall
	
	# print out the binary value
	la $a0, bValue
	li $v0, 4
	syscall
	
	move $a0, $t0
	li $v0, 35
	syscall

	###################################### second random value
	
	# next line
	la $a0, nxl
	li $v0, 4
	syscall
	syscall
	
	# get the random value
	la $a1, 900
	li $v0, 42
	syscall
	
	add $a0, $a0, 100 # add 100 to value to make range [100,1000)
	move $t1, $a0 # move value to $t1
	syscall
	
	# print out the decimal value value
	la $a0, dValue
	li $v0, 4
	syscall
	
	move $a0, $t1
	li $v0, 1
	syscall
	
	# next Line
	la $a0, nxl
	li $v0, 4
	syscall
	
	# print out the hexadecimal value
	la $a0, hValue
	li $v0, 4
	syscall
	
	move $a0, $t1
	li $v0, 34
	syscall
	
	# next line
	la $a0, nxl
	li $v0, 4
	syscall
	
	# print out the binary value
	la $a0, bValue
	li $v0, 4
	syscall
	
	move $a0, $t1
	li $v0, 35
	syscall
	
	#####################Sub tract the values#########################
	# next Line
	la $a0, nxl
	li $v0, 4
	syscall
	
	#subtrac the two values
	sub $t2, $t0, $t1 
	syscall
	
	# show the new value
	move $a0, $t0
	li $v0, 1
	syscall
	
	la $a0, subtract
	li $v0, 4
	syscall
	
	move $a0, $t1
	li $v0, 1
	syscall
	
	la $a0, equal
	li $v0, 4
	syscall
	
	move $a0, $t2
	li $v0, 1
	syscall
	
	############################# divide by 7, then display
	
	# next Line
	la $a0, nxl
	li $v0, 4
	syscall
	
	# divide by 7, then display
	
	la $t3, 7 # store 7 at $t3
	div $t0, $t3 # divide by seven
	syscall
	
	move $a0, $t0
	li $v0, 1
	syscall
	
	la $a0, divBySeven
	li $v0, 4
	syscall
	
	mflo $a0 # get quotient from lo
	li $v0, 1
	syscall
	
	la $a0, period
	li $v0, 4
	syscall
	
	mfhi $a0 # get remainder from hi
	li $v0, 1
	syscall
	
	# end
	li $v0, 10
	syscall
	