.data
string:	.space 100
char:	.space 4

prompt:		.ascii 	""
biToHexAndDeci:	.ascii	"\n[1] Binary to hexadecimal and decimal\n"
hexToBiAndDeci:	.ascii	"[2] Hexadecimal to binary and decimal\n"
deciToBiAndHex:	.ascii	"[3] Decimal to binary and hexadecimal\n"
exitApp:	.ascii	"[4] Exit\n"
choose:		.asciiz "what is your choice: "

notFound:	.asciiz "\nThis item is not valid, Try again\n"

getNumber:	.asciiz "\nsource number: "
nextLine:	.asciiz "\n"

binaryNumber:	.asciiz "\nBinary number: "
decimalNumber:	.asciiz "\nDecimal Number: "
hexNumber:	.asciiz "\nHexadecimal Number: "


exit:		.asciiz "\nPROGRAM FINISHED"
.text 

main:

	la $a0,	prompt
	li $v0, 4
	syscall
	
	li $v0, 8
	la $a0, string
	la $a1, 2 
	syscall
	
	la $a1, string
	jal choises
	
	j main
	
	
choises:				# input at $a1
	lb $t0, ($a1)
	la $t1, 49
	beq $t0, $t1, binaryToOther
	addi $t1, $t1, 1
	beq $t0, $t1, hexToOther
	addi $t1, $t1, 1
	beq $t0, $t1, decimalToOther
	addi $t1, $t1, 1
	beq $t0, $t1, end
	
	j notAValidChoise
	
#######################################
decimalToOther: 			# input Value at $a1
	
	addi $sp, $sp, -8
	sw $ra, 0($sp)
	sw $a1, 4($sp)
	
	la $a0,getNumber
	li $v0, 4
	syscall
	
	li $v0, 8
	la $a0, string
	la $a1, 10 
	syscall
	
	la $t2, 0
	la $a1, string
	jal decimalConverter
	
	lw $ra, 0($sp)
	lw $a1, 4($sp)
	addi $sp, $sp, 8
	
	la $a0, binaryNumber
	li $v0, 4
	syscall
	
	move $a0, $v1
	li $v0, 35
	syscall
	
	la $a0, hexNumber
	li $v0, 4
	syscall
	
	move $a0, $v1
	li $v0, 34
	syscall
	
	la $a0, nextLine
	li $v0, 4
	syscall
	
	jr $ra
	
decimalConverter:

	lb $t1, ($a1)
	beq $t1, $zero, decimalToOtherDone
	beq $t1, 10, decimalToOtherDone
	
	blt $t1, 48, notAValidChoise
	bgt $t1, 57, notAValidChoise
	
	addi $t1, $t1, -48
	mul $t2, $t2, 10
	add $t2, $t2, $t1
	addi $a1, $a1, 1
	
	j decimalConverter
	
decimalToOtherDone:
	move $v1, $t2
	jr $ra
###################################

hexToOther:
	
	addi $sp, $sp, -8
	sw $ra, 0($sp)
	sw $a1, 4($sp)
	
	la $a0,getNumber
	li $v0, 4
	syscall
	
	li $v0, 8
	la $a0, string
	la $a1, 9
	syscall
	
	la $t1, 0
	la $a1, string
	jal hexadecimalConverter
	
	lw $ra, 0($sp)
	lw $a1, 4($sp)
	addi $sp, $sp, 8
	
	la $a0, binaryNumber
	li $v0, 4
	syscall
	
	move $a0, $v1
	li $v0, 35
	syscall
	
	la $a0, decimalNumber
	li $v0, 4
	syscall
	
	move $a0, $v1
	li $v0, 1
	syscall
	
	la $a0, nextLine
	li $v0, 4
	syscall
	
	jr $ra
	
hexadecimalConverter: # input sting at $a1

	#addi $sp, $sp, -4
	#sw $ra, ($sp)
	
	lb $t0, ($a1)
	beq $t0, $zero, hexadecimalConverterDone
	beq $t0, 10, hexadecimalConverterDone
	
	blt $t0, 48, notAValidChoise
	blt $t0, 58, decimalValueFound
	
	blt $t0, 65, notAValidChoise
	blt $t0, 71, uppercaseLetterFound
	
	blt $t0, 97, notAValidChoise
	blt $t0, 103, lowercaseLetterFound
	
	bgt $t0, 102, notAValidChoise
	
	j end
	
decimalValueFound:
	
	addi $a1, $a1, 1
	addi $t0, $t0, -48
	
	sll $t1, $t1, 4
	add $t1, $t1, $t0

	j hexadecimalConverter
	
uppercaseLetterFound:
	addi $a1, $a1, 1
	addi $t0, $t0, -55
	
	sll $t1, $t1, 4
	add $t1, $t1, $t0
	
	j hexadecimalConverter

lowercaseLetterFound:
	addi $a1, $a1, 1
	addi $t0, $t0, -87
	
	sll $t1, $t1, 4
	add $t1, $t1, $t0
	
	j hexadecimalConverter
	
hexadecimalConverterDone:
	move $v1, $t1
	jr $ra
###################################
binaryToOther:			# input value at $a1
	
	addi $sp, $sp, -8
	sw $ra, 0($sp)
	sw $a1, 4($sp)
	
	la $a0,getNumber
	li $v0, 4
	syscall
	
	li $v0, 8
	la $a0, string
	la $a1, 32
	syscall
	
	la $t1, 0
	la $a1, string
	jal binaryConverter
	
	lw $ra, 0($sp)
	lw $a1, 4($sp)
	addi $sp, $sp, 8
	
	la $a0, hexNumber
	li $v0, 4
	syscall
	
	move $a0, $v1
	li $v0, 34
	syscall
	
	la $a0, decimalNumber
	li $v0, 4
	syscall
	
	move $a0, $v1
	li $v0, 1
	syscall
	
	la $a0, nextLine
	li $v0, 4
	syscall
	
	jr $ra
binaryConverter:# shift one bit to the left then add
	
	lb $t0, ($a1)
	beq $t0, $zero, binaryConverterDone
	beq $t0, 10, binaryConverterDone
	
	blt $t0, 48, notAValidChoise
	bgt $t0, 49, notAValidChoise
	
	addi $t0, $t0, -48
	
	sll $t1, $t1, 1
	add $t1, $t1, $t0
	
	add $a1, $a1, 1
	
	j binaryConverter
	
binaryConverterDone:
	move $v1, $t1
	jr $ra
	
##################################
notAValidChoise:
	la $a0, notFound
	li $v0, 4
	syscall
	
	j main	


end:	
	la $a0, exit
	li $v0, 4
	syscall
	
	li $v0, 10
	syscall
	
	
	
