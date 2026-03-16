import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  demandes = [
    { ref: 'RT-99', type: 'Première demande', date: '10 Jan 2026', status: 'En attente' },
    { ref: 'RT-88', type: 'Renouvellement', date: '05 Jan 2026', status: 'Validée' }
  ];

}