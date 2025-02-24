/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */

#include <queue>
#include <vector>
#include <stdexcept>

using namespace std;

class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        auto compare = [](ListNode* a, ListNode* b) {
            return a->val > b->val;
        };

        priority_queue<ListNode*, vector<ListNode*>, decltype(compare)> minHeap(compare);

        for (ListNode* list : lists) {
            if (list) {
                minHeap.push(list);
            }
        }

        ListNode dummy(0);
        ListNode* tail = &dummy;

        while (!minHeap.empty()) {
            ListNode* minNode = minHeap.top();
            minHeap.pop();

            tail->next = minNode;
            tail = tail->next;

            if (minNode->next) {
                minHeap.push(minNode->next);
            }
        }

        return dummy.next; 
    }

private:
    vector<ListNode*> heap;

    void insert(ListNode* node) {
        if (!node) return;

        heap.push_back(node);
        bubbleUp(heap.size() - 1);
    }

    int extractMin() {
        if (heap.empty()) {
            throw runtime_error("Empty heap");
        }

        int minValue = heap[0]->val;t(index);
        }
    }
        heap.pop_back();

        if (!heap.empty()) {
            bubbleDown(0);
        }

        return minValue;
    }

    int peek() {
        if (heap.empty()) {
            throw runtime_error("Empty heap.");
        }
        return heap[0]->val;
    }

    void bubbleUp(int index) {
        while (index > 0 && heap[index]->val < heap[parent(index)]->val) {
            swap(heap[index], heap[parent(index)]);
            index = parent(index);
        }
    }

    void bubbleDown(int index) {
        int smallest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < heap.size() && heap[left]->val < heap[smallest]->val) {
            smallest = left;
        }

        if (right < heap.size() && heap[right]->val < heap[smallest]->val) {
            smallest = right;
        }

        if (smallest != index) {
            swap(heap[index], heap[smallest]);
            bubbleDown(smallest);
        }
    }
    
    int parent(int index) {
        return (index - 1) / 2;
    }

    int leftChild(int index) {
        return 2 * index + 1;
    }
    
    int rightChild(int index) {
        return 2 * index + 2;
    }
};
