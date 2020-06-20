import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Route} from '@angular/router';
import {Group} from '../modell/group';
import {GroupService} from '../services/group.service';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  group: Group;

  constructor(
    private route: ActivatedRoute,
    private groupService: GroupService
  ) { }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('groupID');
    this.groupService.getGroup(id).subscribe(gr => this.group = gr);
  }

}
