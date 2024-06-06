.data
count:	.word 0, 0
array:	.space 80
msg1:	.asciiz "there are "
msg2:	.asciiz " even numbers and "
msg3:	.asciiz " odd numbers in array."
.text

main:
	
	la $a0, array
	la $a1, 20				# size of Array
	la $a2, 91				# max number
	la $a3, 10				# min number
	jal fillArrayWithRandomNumbers		# fill arrays with numbers
	
	la $a1, array
	la $a2, 20				# size of Array
	jal countEvenOddNumbers			# count even and odd numbers in array
	
	lw $t0, 0($v1)				# get even number count
	lw $t1, 4($v1)				# get odd number count 
	
	la $a0, msg1				# There are
	li $v0, 4
	syscall
	
	move $a0, $t0				# {even number count}
	li $v0, 1
	syscall
	
	la $a0, msg2				# even numbeesr and 
	li $v0, 4
	syscall
	
	move $a0, $t1				# {odd number count}
	li $v0, 1
	syscall
	
	la $a0, msg3				# odd numbers in array
	li $v0, 4
	syscall
	
	
	li $v0, 10
	syscall
	
fillArrayWithRandomNumbers:			# array at $a0, length at $a1, max number $a2, min number $a3
	beqz $a1, filledArray
	addi $sp, $sp, -20
	sw $ra, 0($sp)
	sw $a0, 4($sp)				# array
	sw $a1, 8($sp)				# length
	sw $a2, 12($sp)				# min
	sw $a3, 16($sp)				# max
	
	move $a1, $a2
	move $a2, $a3	
	jal generateRandomNumber
	
	lw $t1, 4($sp)
	sw $v1, ($t1)
	
	
	
	lw $a3, 16($sp)
	lw $a2, 12($sp)
	
	lw $a1, 8($sp)
	addi $a1, $a1, -1
	
	lw $a0, 4($sp)
	addi $a0, $a0, 4
	
	lw $ra, 0($sp)
	addi $sp, $sp, 20
	j fillArrayWithRandomNumbers
	
filledArray:
	jr $ra
	
	
countEvenOddNumbers: 				# array at $a1, length at $a2

	beqz $a2, returnCountEvenOddNumbers
	addi $sp, $sp, -16			
	sw $ra, 0($sp)				# Save $ra
	
	addi $a2, $a2, -1			# subtract 4 from size
	lw  $t1, ($a1)				# loads value from array at location $t1
	
	sw $a2, 4($sp)				# save $a2(array size)
	sw $a1, 8($sp)				# save $a1(array)
	
	move $a1, $t1				# give number to isEvenEnumber function
	jal isEvenNumber        		# returns 1 if even, 0 if not even
	
	move $a1, $v1
	jal evenOddCounter			# add to counter array
	
	lw $a1, 8($sp)				# get array and put it at $a1
	addi $a1, $a1, 4			# increment to get next value
	
	lw $a2, 4($sp)				# get array size and put it at $a2
	lw $ra, ($sp)				# get $ra
	addi $sp, $sp, 16
	
	j countEvenOddNumbers

evenOddCounter:

	# I had all of this in the countEvenOddNumber fucntion but it kept calling itself creating an infinite loop

	sw $ra, 12($sp)
	
	beqz $a1, notEvenCounter
	bnez $a1, evenCounter
	
	lw $ra, 12($sp)


evenCounter:
	la $t1, count			# get array
	lw $t2, 0($t1)			# get first value in array
	
	addi $t2, $t2, 1		# add to count
	sw $t2, 0($t1) 			# put value back in array
	
	move $v1, $t1			# return array
	
	jr $ra
	
notEvenCounter:
	la $t1, count			# get arary
	lw $t2, 4($t1)			# get second value in array
	
	addi $t2, $t2, 1		# add to count
	sw $t2, 4($t1) 			# put value back in array
	
	move $v1, $t1			# return array
	
	jr $ra
	

returnCountEvenOddNumbers:
	la $v1, count
	jr $ra
	
generateRandomNumber:
	# upper at $a1 lower at $a2
	# return at $v1
	li $v0, 42
	syscall
	
	add $a0, $a0, $a2
	move $v1, $a0
	jr $ra


isEvenNumber:
	# checks if number at $a1 is even
	# puts the return value (1 for true, 0 for false), at $v1
	la $t0, 2
	div $a1, $t0
	mfhi $t0
	beqz $t0, itsEven
	bnez $t0, itsNotEven
	
itsEven:
	la $v1, 1
	jr $ra

itsNotEven:
	la $v1, 0
	jr $ra
