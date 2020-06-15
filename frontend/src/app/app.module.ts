import {BrowserModule, HAMMER_GESTURE_CONFIG, HammerGestureConfig, HammerModule} from '@angular/platform-browser';
import {Injectable, NgModule} from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ZutatenListeComponent } from './zutaten-liste/zutaten-liste.component';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import { RezepteListViewComponent } from './rezepte-list-view/rezepte-list-view.component';
import { AppRoutingModule } from './app-routing.module';
import { MenueBarComponent } from './menue-bar/menue-bar.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {ReactiveFormsModule} from '@angular/forms';
import { RezeptOverviewComponent } from './rezepte-list-view/rezept-overview/rezept-overview.component';
import { GroupComponent } from './group/group.component';

export class MyHammerCfg extends HammerGestureConfig {
  overrides =  {
    pinch: {enable: false},
    rotate: {enable: false}
  };
}

@NgModule({
  declarations: [
    AppComponent,
    ZutatenListeComponent,
    RezepteListViewComponent,
    MenueBarComponent,
    RezeptOverviewComponent,
    GroupComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatButtonModule,
    MatFormFieldModule,
    MatSelectModule,
    AppRoutingModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    ReactiveFormsModule,
    HammerModule
  ],
  providers: [
    {
      provide: HAMMER_GESTURE_CONFIG,
      useClass: MyHammerCfg
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
