import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { AuthentificationService } from '../../services/authentification.service';

@Component({
    selector: 'app-mot-de-passe-oublie',
    standalone: true,
    imports: [CommonModule, FormsModule, RouterModule],
    templateUrl: './mot-de-passe-oublie.component.html',
    styleUrls: ['./mot-de-passe-oublie.component.css']
})
export class MotDePasseOublieComponent {
    email: string = '';

    constructor(
        private authService: AuthentificationService,
        private router: Router
    ) { }

    onSubmit(): void {
        if (this.email) {
            this.authService.sendVerificationCode(this.email).subscribe(success => {
                if (success) {
                    this.router.navigate(['/authentification/verification-email'], {
                        queryParams: { email: this.email, mode: 'reset' }
                    });
                }
            });
        }
    }
}
