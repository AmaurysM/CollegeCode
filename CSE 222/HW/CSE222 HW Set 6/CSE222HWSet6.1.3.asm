	.data
msgNum:		.asciiz "The number "
isPowerOf2:	.asciiz " is the power of 2"
isNotPowerOf2:	.asciiz " is not the power of 2"
		
	.text
main:	
	la $t0, 2		#load 2, used to divide
	
	la $a1, 100
	li $v0, 42
	syscall
	
	move $t1, $a0		# save given number
	
	jal divisable
	
	j end
	
divisable:
	
	move $s0, $ra		# save adress
	div $t1, $t0		# div by 2
	mfhi $t2
	
	beqz $t2, yes		# if it equals 0, yes
	bnez $t2, no		# if does not equal 0, no
	
	jr $s0
	
yes:
	la $a0, msgNum
	li $v0, 4
	syscall
	
	move $a0, $t1
	li $v0, 1
	syscall
	
	la $a0, isPowerOf2
	li $v0, 4
	syscall
	
	jr $s0
	
no:
	la $a0, msgNum
	li $v0, 4
	syscall
	
	move $a0, $t1
	li $v0, 1
	syscall
	
	la $a0, isNotPowerOf2
	li $v0, 4
	syscall
	
	jr $s0
	
end: 
	li $v0, 10
	syscall
