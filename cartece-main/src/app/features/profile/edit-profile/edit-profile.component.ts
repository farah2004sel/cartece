import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../../core/services/auth.service';
import { Profile } from '../../../core/models/profile.model';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  loading = false;

  profileData: Profile = {
    fullName: '',
    birthDate: '',
    birthPlace: '',
    nationality: '',
    residenceDate: '',
    address: '',
    type: 'physique'
  };

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.authService.personType.subscribe(t => {
      this.profileData.type = t;
    });
  }
  save(form: NgForm) {
    if (!form.valid) {
      alert('Veuillez remplir tous les champs obligatoires !');
      return;
    }
  
    this.loading = true;
    this.authService.setProfileData(this.profileData);
  
    setTimeout(() => {
      this.loading = false;
      alert('Profil enregistré avec succès !');
      this.router.navigate(['/accueil/commercant']);
    }, 1000);
  }}