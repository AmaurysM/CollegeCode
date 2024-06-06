	.data
var:	.word  0x12345678
small:	.asciiz "Little-endian"
big:	.asciiz "Big-endian"

	.text
	
main:	
	la $a0, var
	lw $t0, 0($a0)
	
	lbu $t1, 0($a0)
	lbu $t2, 1($a0)
	lbu $t3, 2($a0)
	lbu $t4, 3($a0)
	
	bgt $t1, $t4, smallEnd
	
	la $a0, big
	li $v0, 4
	
	j end
			
smallEnd:
	la $a0, small
	li $v0, 4
	syscall
	
	j end
	
end:
	li $v0, 10
	syscall