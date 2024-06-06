	.data
	
nxl:	.asciiz "\n"

max:	.asciiz "This is the max: "
min:	.asciiz "This is the min: "
ave:	.asciiz "This is the average: "

	.text
	
main:
	la $t0, 0
	la $t4, 101
	
	la $a1, 91
	
	
loop:
	beq $t0, 20, average
	li $v0, 42
	syscall
	addi $a0, $a0, 10
	
	add $t1, $t1, $a0
	move $t2, $a0
	addi $t0, $t0, 1
	
	bge $t2, $t3, greaterValue
	blt $t2, $t3, lesserValue
	
greaterValue:
	move $t3, $t2
	j loop
	
lesserValue:
	ble $t2, $t4, smallestValue
	j loop
	
smallestValue:
	move $t4, $t2
	j loop

average:
	div $t1, $t0
	j print
	
print:
	
	la $a0, max
	li $v0, 4
	syscall
	
	move $a0, $t3
	li $v0, 1
	syscall
	
	la $a0, nxl	# next line
	li $v0, 4
	syscall
	
	la $a0, min
	li $v0, 4
	syscall
	
	move $a0, $t4
	li $v0, 1
	syscall
	
	la $a0, nxl	# next line
	li $v0, 4
	syscall
	
	la $a0, ave
	li $v0, 4
	syscall
	
	mflo $a0
	li $v0, 1
	syscall
	
	j end
	
end:
	li $v0, 10
	syscall