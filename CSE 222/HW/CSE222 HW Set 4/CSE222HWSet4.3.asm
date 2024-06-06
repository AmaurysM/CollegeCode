	.data
vx: 	.word 0
prompt: .asciiz  "the random number is "
nxl:	.asciiz "\n" 			# next Line
tthree:	.asciiz "3 * vx = "
shiftL: .asciiz "vx << 1 + vx = "
addTtimes: .asciiz "vx +  vx + vx = "

	.text

main:
	# generate a random number
    	li $a1, 201        
    	li $v0, 42         

    	syscall
    	add $a0, $a0, -101  
	move $t0, $a0
	
   	 # display the random number
    	la $a0, prompt     
   	li $v0, 4          
    	syscall
    
   	move $a0, $t0      
    	li $v0, 1      
    	
    	syscall
    	
	la $a0, nxl  
	li $v0, 4
	syscall

	
    	# save the number in vx
    	la $t1, vx
   	sw $t0, vx
   	la $t0, 3
	syscall
	
	lw $t2, 0($t1)
	mult $t0, $t2
	syscall
	
	la $a0,tthree
	li $v0, 4
	syscall
	
	mflo $a0
	li $v0, 1
	syscall
	
	# shift left
	
	la $a0, nxl  
	li $v0, 4
	syscall
	
	la $a0, shiftL  
	li $v0, 4
	syscall
	
	sll $t3, $t2, 1 
	add $a0, $t3, $t2
	li $v0, 1
	syscall
	
	# add three times
	la $a0, nxl  
	li $v0, 4
	syscall
	
	la $a0, addTtimes  
	li $v0, 4
	syscall
	
	add $t3, $t2, $t2
	add $a0, $t3, $t2
	li $v0, 1
	syscall
	
   	# end
    	li $v0, 10         
    	syscall
    	
    	