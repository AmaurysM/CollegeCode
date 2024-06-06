	.data
nxl:		.asciiz "\n"

numEven:	.asciiz "This number is even"
numOdd:		.asciiz "This number is Odd"


isDivisible: 	.asciiz "yes, it is divisible ty 3 and 5"
isNotDivisible: .asciiz "no, it is not divisible ty 3 and 5"

isDivisibleBySevenAndNine:	.asciiz "This number is Divisible By 7 and 9"
isNotDivisibleBySevenAndNine:	.asciiz "This number is not Divisible By 7 and 9"

isDivBySeven:	.asciiz "This Number is divisible By 7"
isDivByNine:	.asciiz "This Number is divisible By 9"
isNotDivBySevenOrNine:	.asciiz "This number is not divisible by 7 or 9"


	.text
main:
	la $s0, 0 # counting number
	la $s1, 3 # used for loop
	
	la $s2, 2 # used to div by 2
	
	li $v0, 42
	la $a1, 999
	syscall
	
	add $a0, $a0, 2
	move $t0, $a0
	li $v0, 1
	syscall

start:
	
	# next line
	la $a0, nxl
	li $v0, 4
	syscall

first:	
	bne $s0, 0, second # check if the counter is 0, if not go to second
	
	# 1) shift left then right, if they are eaqual, 
	#    the number is even
	srl $t1, $t0, 1
	sll $t1, $t1, 1
	
	beq $t1, $t0, even
	bne $t1, $t0, odd
	
	j end

second:	
	bne $s0, 1, third # check if the counter is 1, if not go to thrid
	
	# 2) shift left 31 bits, if what ever is left is 0,
	#    then the number is even
	
	sll $t2, $t0, 31
	beqz $t2, even
	bnez $t2, odd
	
	j end
	
third:
	bne $s0, 2, end # check if the counter is 2, if not go to end
	
	# 3) divide by 2, if there is no remainder then 
	#   the number is even
	
	div $t0, $s2
	mfhi $t3
	beqz $t3, even
	bnez $t3, odd
	
	j end
	
even:			# check if the number is even
	addi $s0, $s0, 1
	la $a0, numEven
	li $v0, 4
	syscall
	
	
	j end
	
odd:			# check if the number is odd
	addi $s0, $s0, 1
	la $a0, numOdd
	li $v0, 4
	syscall
	
	j end
	
divisible:		# check if the number is divisible

	# next line
	la $a0, nxl
	li $v0, 4
	syscall
	
	addi $s0, $s0, 1
	la $s4, 3
	la $s5, 5
	
	la $s6, 7
	la $s7, 9
	
	div $t0, $s4	# div by 3
	mfhi $t4
	
	div $t0, $s5	# div by 5
	mfhi $t5
	
	div $t0, $s6	# div by 7
	mfhi $t6
	
	div $t0, $s7	# div by 9
	mfhi $t7
	
threeAndFive:
	beq $t4, $t5, sameNum
	bne $t4, $t5, notDivBy3and5
	
	j end

sameNum:
	beqz $t4, divBy3and5
	bnez $t4, notDivBy3and5
	
	j sevenOrNine

divBy3and5:
	la $a0, isDivisible
	li $v0, 4
	syscall
	
	j sevenOrNine

notDivBy3and5:
	la $a0, isNotDivisible
	li $v0, 4
	syscall
	
	j sevenOrNine
	
sevenOrNine:
	# next line
	la $a0, nxl
	li $v0, 4
	syscall
	
	beq $t6, $t7, sevenAndNine
	bne $t6, $t7, divisibleBySeven
	
	j end

divisibleBySeven:
	beqz $t6, divBySeven
	beqz $t7, divByNine
	
	j notDivBySevenOrNine

divBySeven:
	la $a0, isDivBySeven
	li $v0, 4
	syscall
	
	j end
	
divByNine:
	la $a0, isDivByNine
	li $v0, 4
	syscall
	
	j end

notDivBySevenOrNine:
	la $a0, isNotDivBySevenOrNine
	li $v0, 4
	syscall
	
	j end

sevenAndNine:
	beqz $t6, divByNineAndSeven
	beqz $t6, notDivByNineAndSeven
	
	j end

divByNineAndSeven:
	la $a0, isDivisibleBySevenAndNine
	li $v0, 4
	syscall
	
	j end
	
notDivByNineAndSeven:	
	la $a0, isNotDivisibleBySevenAndNine
	li $v0, 4
	syscall
	
	j end

end:
	blt $s0, $s1, start
	beq $s0, $s1, divisible
	
	li $v0, 10
	syscall