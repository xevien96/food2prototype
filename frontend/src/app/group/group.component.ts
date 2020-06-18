import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Route} from '@angular/router';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  id: number;

  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('groupID');
  }

}
