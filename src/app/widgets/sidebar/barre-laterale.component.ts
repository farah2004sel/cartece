import { Component } from '@angular/core';

@Component({
  selector: 'app-barre-laterale',
  template: `
    <aside class="sidebar">
      <nav>
        <ul>
          <li><a routerLink="/accueil">Accueil</a></li>
          <li><a routerLink="/administration">Tableau de bord Admin</a></li>
          <li><a routerLink="/commercant/tableau-de-bord">Tableau de bord Commerçant</a></li>
          <li><a routerLink="/employes/liste">Employés</a></li>
          <li><a routerLink="/chatbot/discuter">Chatbot</a></li>
        </ul>
      </nav>
    </aside>
  `,
  styles: [`
    .sidebar { width: 250px; background: #f4f4f4; height: 100vh; padding: 20px; }
    ul { list-style: none; padding: 0; }
    li { margin-bottom: 10px; }
  `]
})
export class BarreLateraleComponent { }
