	.data
var:	.word 0

itsMonday: 	.asciiz "Monday"
itsTuesday: 	.asciiz "Tuesday"
itsWednesday: 	.asciiz "Wednesday"
itsThursday: 	.asciiz "Thursday"
itsFriday: 	.asciiz "Friday"
itsSaturday: 	.asciiz "Saturdayy"
itsSunday: 	.asciiz "Sunday"

	.text
main:
	la $a1, 7	# max number
	li $v0, 42	# random number
	syscall
	
	la $t0, var	# load var
	sw $a0, 0($t0)	# save random number in var
	
	lw $t1, 0($t0)	# take the value of var and put it in $t1
	
	beq $t1, 1, monday
	beq $t1, 2, tuesday
	beq $t1, 3, wednesday
	beq $t1, 4, thursday
	beq $t1, 5, friday
	beq $t1, 6, saturday
	beq $t1, 0, sunday
	
monday:
	la $a0, itsMonday
	li $v0, 4
	syscall
	
	j end
	
tuesday:
	la $a0, itsTuesday
	li $v0, 4
	syscall
	
	j end
	
wednesday:
	la $a0, itsWednesday
	li $v0, 4
	syscall
	
	j end
	
thursday:
	la $a0, itsThursday
	li $v0, 4
	syscall
	
	j end
	
friday:
	la $a0, itsFriday
	li $v0, 4
	syscall
	
	j end
	
saturday:
	la $a0, itsSaturday
	li $v0, 4
	syscall
	
	j end
	
sunday:
	la $a0, itsSunday
	li $v0, 4
	syscall
	
	j end
	

	
end:
	li $v0, 10
	syscall