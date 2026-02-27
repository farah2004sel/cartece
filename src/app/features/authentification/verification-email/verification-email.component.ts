import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
    selector: 'app-verification-email',
    standalone: true,
    imports: [CommonModule, FormsModule, RouterModule],
    templateUrl: './verification-email.component.html',
    styleUrls: ['./verification-email.component.scss']
})
export class VerificationEmailComponent implements OnInit {
    email: string = '';
    otp: string[] = ['', '', '', '', '', ''];
    countdown: number = 60;
    canResend: boolean = false;
    timer: any;

    constructor(
        private route: ActivatedRoute,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.route.queryParams.subscribe(params => {
            this.email = params['email'] || 'votre email';
        });
        this.startTimer();
    }

    startTimer(): void {
        this.canResend = false;
        this.countdown = 60;
        this.timer = setInterval(() => {
            if (this.countdown > 0) {
                this.countdown--;
            } else {
                this.canResend = true;
                clearInterval(this.timer);
            }
        }, 1000);
    }

    onOtpChange(value: string, index: number): void {
        if (value && index < 5) {
            const nextInput = document.getElementById(`otp-${index + 1}`) as HTMLInputElement;
            if (nextInput) nextInput.focus();
        }
        this.otp[index] = value;
    }

    onKeyDown(event: KeyboardEvent, index: number): void {
        if (event.key === 'Backspace' && !this.otp[index] && index > 0) {
            const prevInput = document.getElementById(`otp-${index - 1}`) as HTMLInputElement;
            if (prevInput) prevInput.focus();
        }
    }

    verify(): void {
        const code = this.otp.join('');
        if (code.length === 6) {
            console.log('Verifying code:', code);
            // Logic for verification here
            // For now, redirect to login or profile
            this.router.navigate(['/authentification/connexion']);
        }
    }

    resendCode(): void {
        if (this.canResend) {
            console.log('Resending code to:', this.email);
            this.startTimer();
        }
    }
}
