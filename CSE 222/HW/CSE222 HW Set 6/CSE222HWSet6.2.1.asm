	.data
array:	.space 16

	.text
main:	
	la $t0, array
	
	jal fillArray		# fills the array with values
	
	
	la $t1, 0		# i = 0
	la $s0, 16		# i <= 16
	jal printLoop		# prints the values in an Array
	
	j end
	
fillArray:			# fills the array with numbers 1 - 4
	la $t1, 1
	sw $t1, 0($t0)
	
	la $t2, 2
	sw $t2, 4($t0)
	
	la $t3, 3
	sw $t3, 8($t0)
	
	la $t4, 4
	sw $t4, 12($t0)
	
	jr $ra
	
printLoop:			# takes array at $t0, and displays every value in that array
	beq $t1, $s0, loopEnd
	
	lw $a0, ($t0)
	li $v0, 1
	syscall
	addi $t1, $t1, 4
	addi $t0, $t0, 4
	j printLoop
	
loopEnd:
	jr $ra
	
end:
	la $v0, 10
	syscall
