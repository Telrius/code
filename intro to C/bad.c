/*
 ============================================================================
 Name        : bad.c
 Author      : Some bad programmer
 Description : A bad program... you'll see why...
 ============================================================================
 */

#include <stdio.h>
#include <string.h>

int main(void)
{
	// you only need these two lines if you are working in Eclipse
	setvbuf(stdout, NULL, _IONBF, 0);
	setvbuf(stderr, NULL, _IONBF, 0);

	char userPassword[15];

	int pass = 0;
	int action = 0;

	// Load password from file and store in pass
	FILE *ptr_file;
	char filePassword[15];

	ptr_file=fopen("data.txt","r");
	if (!ptr_file)
		return 1;

	while (fgets(filePassword,1000, ptr_file)!=NULL)

	fclose(ptr_file);


	// Get password from user
	printf("\n Enter the password : \n");
	gets(userPassword);

	// Check password against the user entered password
	if(strcmp(userPassword, filePassword) != 0){
		printf("\n Wrong Password \n");
	}
	else{
		printf("\n Correct Password \n");
		pass = 1;
	}

	if(pass) {
        // Now give special privileges to the user
		printf("\n Special privileges given to the user \n");

		while(1){
			printf("Choose action \n");
			printf("0 - Show password \n");
			printf("1 - Replace password \n");
			printf("2 - Exit \n");

			scanf("%d", &action);
			fflush(stdin);

			// Show password
			if(action == 0){
				FILE *ptr_file;
				char buf[15];

				ptr_file=fopen("data.txt","r");
				if (!ptr_file)
					return 1;

				while (fgets(buf,1000, ptr_file)!=NULL)

				fclose(ptr_file);
				printf("%s \n", buf);
			}
			// Replace password
			else if(action == 1){

				char password[15] = "test";
				FILE *ptr_file;

				ptr_file =fopen("data.txt", "w");

				if (!ptr_file)
					return 1;

				printf("Enter new password: ");
				gets(password);

				fprintf(ptr_file,"%s", password);

				fclose(ptr_file);
			}
			// Exit
			else
				return 0;
		}
	}
	return 0;
}
