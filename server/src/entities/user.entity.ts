
import { Entity, Column, PrimaryGeneratedColumn, OneToMany } from 'typeorm';
import { ChatRoom } from './chatroom';

@Entity()
export class User {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  name: string;

  @Column()
  email: string;

  @Column()
  city: string;

  @Column({ default: true })
  isActive: boolean;

  @Column()
  password: string;

  @OneToMany(type => ChatRoom, chatroom => chatroom.owner)
  rooms: ChatRoom[];
}
