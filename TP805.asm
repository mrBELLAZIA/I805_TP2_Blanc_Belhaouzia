DATA SEGMENT
DATA ENDS
CODE SEGMENT
	mov eax, 4
	push eax
	mov eax, 4
	pop ebx
	add eax, ebx
	push eax
	mov eax, 4
	pop ebx
	add eax, ebx
CODE ENDS