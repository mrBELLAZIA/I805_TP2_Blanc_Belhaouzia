DATA SEGMENT
DATA ENDS
CODE SEGMENT
	mov eax, 0
	push eax
	mov eax, 4
	pop ebx
	sub eax, ebx
	jle faux_lt_1
	mov eax, 1
	jmp sortie_lt_1
faux_lt_1 :
	mov eax, 0
sortie_lt_1 :
	jz faux_and_1
	mov eax, 7
	push eax
	mov eax, 8
	pop ebx
	sub eax, ebx
	jge faux_gt_1
	mov eax, 1
	jmp sortie_gt_1
faux_gt_1 :
	mov eax, 0
sortie_gt_1 :
faux_and_1 :
	jz faux_if_1
	mov eax,  1
	out eax
	jmp sortie_if_1
faux_if_1 :
	mov eax,  2
	out eax
sortie_if_1 :
CODE ENDS