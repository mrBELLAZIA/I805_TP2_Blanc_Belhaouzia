DATA SEGMENT
	 a DD
DATA ENDS
CODE SEGMENT
	mov eax, 7
	mul eax, -1
	mov a, eax
	mov eax,  a
	out eax
CODE ENDS