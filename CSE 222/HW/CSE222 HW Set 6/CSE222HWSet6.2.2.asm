	.data
array:		.space 16
shufledArray:	.space 16

start:	.asciiz "["
middle: .asciiz ", "
finish:	.asciiz "]"

	.text
main:
	la $t0, 1
	la $t1, 0
	la $t2, 16
	jal fillArray
	
	la $a0, start		# open: [
	li $v0, 4
	syscall
		
	la $t0, array
	la $t1, 0		# i = 0
	la $s0, 16		# i <= 16
	jal printLoop
	
	la $a0, finish		# close: ]
	li $v0, 4
	syscall
	
	
	la $t0, array
	la $t1, 0
	la $s0, 16
	la $t2, 4 	# used to find random number
	la $t3, 0
	la $t4, 4	# mult by 4
	jal shuffle
	
	la $a0, start		# open: [
	li $v0, 4
	syscall
	
	la $t0, shufledArray
	la $t1, 0		# i = 0
	la $s0, 16		# i <= 16
	jal printLoop
	
	la $a0, finish		# close: ]
	li $v0, 4
	syscall
	
	j end

fillArray:
	beq $t1, $t2, arrayFilled
	sw  $t0, array($t1)
	
	addi $t0, $t0, 1
	addi $t1, $t1, 4
	
	j fillArray
	
arrayFilled:
	jr $ra
	
printLoop:			# takes array at $t0, and displays every value in that array
	beq $t1, $s0, loopEnd
	
	lw $a0, ($t0)
	li $v0, 1
	syscall
	
	la $a0, middle
	li $v0, 4
	syscall
	
	addi $t0, $t0, 4
	addi $t1, $t1, 4
	j printLoop
	
loopEnd:
	jr $ra
	
shuffle:# pick a random number in the array. 
	# This is done by picking a random number from [0,5) and then multiplying it by 4,
	# to get the location of that value in the array. 
	# If its not the last number in the array, 
	# swap the value picked whith the last value in the array. 
	# Then dicrement $a1(the max number that can be picked)
	# If it is the last number in the array, just decrease $a1
	# The repeat
	
	beqz $t2, shuffleEnd
	move $a1, $t2
	li $v0, 42
	syscall
	
	mult $a0, $t4
	mflo $t5			# location of picked number
	
	lw $t6, array($t5) 
	sw $t6, shufledArray($t1)	# put number in new array, at location $t1
	
	addi $t2, $t2, -1
	addi $t1, $t1, 4

	mult $t2, $t4			
	mflo $t7			# location of last number in the array
	
	bne $t5, $t7, didNotPickEndOfArray
	
	j shuffle

shuffleEnd:
	jr $ra
	
didNotPickEndOfArray:
	lw $s1, array($t7)
	sw $s1, array($t5)
	j shuffle
	
end:
	li $v0, 10
	syscall
	
