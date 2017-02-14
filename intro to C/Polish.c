/*
 ============================================================================
 Name        : Polish.c
 Author      : Borrowed from http://www.learntosolveit.com/cprogramming/sec_4.3.html
 Description : This program has number of helper functions like getop, push and pop,
 	 	 	   which we use to the implement the reverse polish notation calculator.
			   The function getop takes a string and determines if it is number.
			   If it is a number, both integer or decimal, it will store that number
			   in the array and return a flag NUMBER which states that number is found.
			   It will push that number to the stack. If it getop returns an operator like +,
			    -, * or /, it will pop two numbers out of the stack and operate on it.
			    When it encounters a /, it ensures that the second operand is not 0 and disallows.

			    Run Examples:
			    5	2	+
			    will output 7

				8	4	-	2	*
				will output 8

				develop checklist of items
				http://cppcheck.sourceforge.net/
 ============================================================================
 */


#include <stdio.h>
#include <stdlib.h> /* for atof() */
#define MAXOP 100 /* max size of operand or operator */
#define NUMBER '0' /* signal that a number was found */

// functions
int getop(char []);
void push(double);
double pop(void);

int main(void)
{
	setvbuf(stdout, NULL, _IONBF, 0); // Only need in Eclipse
	setvbuf(stderr, NULL, _IONBF, 0); // Only need in Eclipse

    int type;
    double op2;
    char s[MAXOP];
    while ((type = getop(s)) != EOF) {
        switch (type) {
            case NUMBER:
            push(atof(s));
            break;
            case '+':
            push(pop() + pop());
            break;
            case '*':
            push(pop() * pop());
            break;
            case '-':
            op2 = pop();
            push(pop() - op2);
            break;
            case '/':
            op2 = pop();
            if (op2 != 0.0)
                push(pop() / op2);
            else
                printf("error: zero divisor\n");
            break;
            case '\n':
            printf("\t%.8g\n", pop());
            break;
            default:
            printf("error: unknown command %s\n", s);
            break;
        }
    }
    return 0;
}

#define MAXVAL 100

int sp = 0;
double val[MAXVAL];

void push(double f)
{
    if(sp < MAXVAL)
        val[sp++]=f;
    else
        printf("error:stack full, cant push %g\n",f);
}

double pop(void)
{
	return val[--sp];
}

#include<ctype.h>

int getch(void);
void ungetch(int);

int getop(char s[])
{
    int i,c;

    while((s[0] = c = getch()) == ' ' || c =='\t')
        ;
    s[1] = '\0';

    i = 0;
    if(!isdigit(c) && c!='.' && c!='-')
        return c;

    if(c=='-')
        if(isdigit(c=getch()) || c == '.')
            s[++i]=c;
        else
        {
            if(c!=EOF)
                ungetch(c);
            return '-';
        }

    if(isdigit(c))
        while(isdigit(s[++i] =c =getch()))
            ;

    if(c=='.')
        while(isdigit(s[++i] = c=getch()))
            ;

    s[i] = '\0';
    if(c!=EOF)
        ungetch(c);

    return NUMBER;
}

#define BUFSIZE 100

char buf[BUFSIZE];
int bufp = 0;

int getch(void)
{
    return (bufp > 0) ? buf[--bufp] : getchar();
}

void ungetch(int c)
{
    buf[bufp++] = c;
}
