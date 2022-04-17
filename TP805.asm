DATA SEGMENT
	 a DD
	 b DD
	 c DD
DATA ENDS
CODE SEGMENT
	in eax
	mov a, eax
	in eax
	mov b, eax
	in eax
	mov c, eax
	mov eax, a
	push eax
	mov eax, b
	pop ebx
	sub eax, ebx
	jge faux_gt_1
	mov eax, 1
	jmp sortie_gt_1
faux_gt_1 :
	mov eax, 0
sortie_gt_1 :
	jnz faux_not_1
	mov eax, 1
	jmp sortie_not_1
faux_not_1 :
	mov eax, 0
sortie_not_1 :
	jz faux_and_1
	mov eax, b
	push eax
	mov eax, c
	pop ebx
	sub eax, ebx
	jl faux_lte_1
	mov eax, 1
	jmp sortie_lte_1
faux_lte_1 :
	mov eax, 0
sortie_lte_1 :
faux_and_1 :
	jz faux_if_1
	mov eax,  a
	out eax
	jmp sortie_if_1
faux_if_1 :
	mov eax,  c
	out eax
sortie_if_1 :
CODE ENDS