	.data

nxl:	.asciiz "\n"
word:	.word 0

rightShift:	.word 0
leftShift:	.word 23

	.text
main:

	la $t0, 0x12345678 	# loads hex number
	
	sll $t1, $t0, 1 	# shift left by 1
	move $a0, $t1
	li $v0, 34		# print in hex
	syscall
	
	la $a0, nxl		# next line
	li $v0, 4
	syscall
			
	srl $t2, $t1, 1		# shift right by 1
	move $a0, $t2
	li $v0, 34
	syscall
	
	la $a0, nxl		# next line
	li $v0, 4
	syscall
			
	sra $t3, $t2, 1		# shift right arithmeticaly by 1
	move $a0, $t3
	li $v0, 34
	syscall
	
	la $a0, nxl		# next line
	li $v0, 4
	syscall
	
	# shift right by 24 to get the first 8 bits, 					  EX.(0x00000012) 
	# then shift the original number to the left by 8 to get the rest of the numbers, EX.(0x34567800)
	# then add them together
	srl $t4, $t3, 24
	sll $t5, $t3, 8
	add $t6, $t4, $t5
	move $a0, $t6
	li $v0, 34
	syscall
	
	######################
	
	# this moves the bits from right to left, again and again, picking every other bit
	
	la $s0, 0 	# right shift counter (right shifter), Used to shift bits to the right
	la $s1, 31 	# left shift counter (left shifter), Used to shift bits to the left
	
findEvenBits: 				

	bge $s0, 31, printBinaryNumber
	srlv $s2, $t6, $s0
	sll $s2, $s2, 31	# shifts all the way to the left
	srlv $s2, $s2, $s1
	
	add $s3, $s3, $s2 	# adds all the numbers together
	
	addi $s0, $s0, 2 	# adds 2 to the right shifter
	subi $s1, $s1, 2 	# subtracts 2 from left shifter
	
	j findEvenBits
	#######################
	
printBinaryNumber:
	
	la $a0, nxl	# next line
	li $v0, 4
	syscall
	
	move $a0, $t6   # number
	li $v0, 35
	syscall
	
	la $a0, nxl	# next line
	li $v0, 4
	syscall
	
	move $a0, $s3	# only even
	li $v0, 35
	syscall
	
end:	

	li $v0, 10
	syscall