	.data
msg:	.asciiz "Give me a number: "
	
	.text
main:	
	la $t0, 2		#load 2, used to divide
	
	la $a0, msg		# send message
	li $v0, 4
	syscall
	
	li $v0, 5
	syscall			# get number
	
	move $t1, $v0		# save given number
	
	jal divisable
	
	j end
	
divisable:
	
	move $s0, $ra		# save adress
	
	srl $t2, $t1, 1		# shift right
	sll $t2, $t2, 1		# Then shift left
	
	beq $t1, $t2, yes	# checks if its the same as original number
	bne $t1, $t2, no	# checks if the original nmber is different from the second one
	
yes:
	la $a0, 1
	li $v0, 1
	syscall
	
	jr $s0
	
no:
	la $a0, 0
	li $v0, 1
	syscall
	
	jr $s0
	
end: 
	li $v0, 10
	syscall
