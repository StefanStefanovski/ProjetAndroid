
import { Entity, Column, PrimaryGeneratedColumn, ManyToOne } from 'typeorm';
import { User } from './user.entity';

@Entity()
export class ChatMessage {
  @PrimaryGeneratedColumn()
  id: number;

  @ManyToOne(type => User, user => user.rooms)
  owner: User;

  @Column()
  message: string;

  @Column()
  date: Date;
}
