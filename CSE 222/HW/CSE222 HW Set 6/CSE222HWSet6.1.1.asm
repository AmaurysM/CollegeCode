	.data
msg:	.asciiz "Give me a number: "
	
	.text
main:	
	la $t1, 2		#load 2, used to divide
	
	la $a0, msg		# send message
	li $v0, 4
	syscall
	
	li $v0, 5
	syscall			# get number
	
	move $t0, $v0		# save given number
	
	jal divisable
	
	j end
	
divisable:
	
	move $s0, $ra		# save adress
	div $t0, $t1		# div by 2
	mfhi $t2
	beqz $t2, yes		# if it equals 0, yes
	bnez $t2, no		# if does not equal 0, no
	
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
